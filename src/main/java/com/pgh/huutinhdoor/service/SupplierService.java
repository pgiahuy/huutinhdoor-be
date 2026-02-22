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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ImageRepository imageRepository;
    private final ImageService  imageService;

    @Transactional(readOnly = true)
    public Supplier findByIdOrThrow(Long id) {
       return supplierRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Supplier not found with id:" + id));
    }


    @Transactional(readOnly = true)
    public List<Supplier> getAll(){
        return  supplierRepository.findAll();
    }

    @Transactional
    public Supplier create(SupplierCreateRequest request) {
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier savedSupplier = supplierRepository.save(supplier);

        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            imageService.replacePrimaryImage(
                    savedSupplier.getId(),
                    TargetType.SUPPLIER,
                    request.getAvatar(),
                    UploadFolder.SUPPLIER
            );
        }
        return savedSupplier;
    }

    @Transactional
    public Supplier update(Long id, SupplierUpdateRequest request) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Supplier not found with id:" + id));
        EntityUtil.copyNoNullProperties(request, supplier);
        Supplier savedSupplier = supplierRepository.save(supplier);

        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            imageService.replacePrimaryImage(
                    savedSupplier.getId(),
                    TargetType.SUPPLIER,
                    request.getAvatar(),
                    UploadFolder.SUPPLIER
            );
        }

        return savedSupplier;
    }
    @Transactional
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Supplier not found with id:" + id));
        imageService.deleteAllByTarget(id, TargetType.SUPPLIER);
        supplierRepository.delete(supplier);
    }

    @Transactional(readOnly = true)
    public Optional<Image> getPrimaryAvatar(Long supplierId) {
        return imageRepository.findByTargetIdAndTargetTypeAndIsPrimaryTrue(supplierId, TargetType.SUPPLIER);
    }

//    public Image getPrimaryAvatar(Long id) {
//        return imageService.getAvatar(id, TargetType.SUPPLIER);
//    }
}
