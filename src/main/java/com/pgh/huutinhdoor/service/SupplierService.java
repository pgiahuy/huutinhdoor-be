package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.SupplierCreateRequest;
import com.pgh.huutinhdoor.dto.request.SupplierUpdateRequest;
import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.dto.response.admin.SupplierResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.Supplier;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.enums.UploadFolder;
import com.pgh.huutinhdoor.exception.ApiError;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.SupplierMapper;
import com.pgh.huutinhdoor.repository.ImageRepository;
import com.pgh.huutinhdoor.repository.SupplierRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import com.pgh.huutinhdoor.util.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ImageRepository imageRepository;
    private final ImageService  imageService;

    public record SupplierWithAvatar(
            Supplier supplier,
            String avatarUrl
    ) {}


    @Transactional(readOnly = true)
    public Supplier findByIdOrThrow(Long id) {
       return supplierRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Supplier not found with id:" + id));
    }

    @Transactional(readOnly = true)
    public SupplierWithAvatar getWithAvatar(Long id) {

        Supplier supplier = findByIdOrThrow(id);

        String avatarUrl = imageRepository
                .findByTargetIdAndTargetTypeAndIsPrimaryTrue(id, TargetType.SUPPLIER)
                .map(Image::getUrl)
                .orElse(GlobalConstants.SUPPLIER_AVATAR);

        return new SupplierWithAvatar(supplier, avatarUrl);
    }


    @Transactional(readOnly = true)
    public List<SupplierWithAvatar> getAllWithAvatar() {
        List<Supplier> suppliers = supplierRepository.findAll();

        List<Long> ids = suppliers.stream()
                .map(Supplier::getId)
                .toList();

        List<Image> avatars = imageRepository
                .findByTargetTypeAndTargetIdInAndIsPrimaryTrue(
                        TargetType.SUPPLIER,
                        ids
                );

        Map<Long, String> avatarMap = avatars.stream()
                .collect(Collectors.toMap(
                        Image::getTargetId,
                        Image::getUrl
                ));

        return suppliers.stream()
                .map(s -> new SupplierWithAvatar(
                        s,
                        avatarMap.getOrDefault(
                                s.getId(),
                                GlobalConstants.SUPPLIER_AVATAR
                        )
                ))
                .toList();
    }

    @Transactional
    public SupplierWithAvatar createWithAvatar(SupplierCreateRequest request) {
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier savedSupplier = supplierRepository.save(supplier);

        String avatarUrl = GlobalConstants.SUPPLIER_AVATAR;

        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            Image image = imageService.replacePrimaryImage(
                    savedSupplier.getId(),
                    TargetType.SUPPLIER,
                    request.getAvatar(),
                    UploadFolder.SUPPLIER
            );
            avatarUrl = image.getUrl();
        }

        return new SupplierWithAvatar(savedSupplier, avatarUrl);
    }

    @Transactional
    public SupplierWithAvatar update(Long id, SupplierUpdateRequest request) {

        Supplier supplier = findByIdOrThrow(id);
        EntityUtil.copyNoNullProperties(request, supplier);

        String avatarUrl = imageRepository
                .findByTargetIdAndTargetTypeAndIsPrimaryTrue(id, TargetType.SUPPLIER)
                .map(Image::getUrl)
                .orElse(GlobalConstants.SUPPLIER_AVATAR);

        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            Image image = imageService.replacePrimaryImage(
                    supplier.getId(),
                    TargetType.SUPPLIER,
                    request.getAvatar(),
                    UploadFolder.SUPPLIER
            );
            avatarUrl = image.getUrl();
        }

        return new SupplierWithAvatar(supplier, avatarUrl);
    }


    @Transactional
    public void delete(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id:" + id);
        }
        imageService.deleteAllByTarget(id, TargetType.SUPPLIER);
        supplierRepository.deleteById(id);
    }

}
