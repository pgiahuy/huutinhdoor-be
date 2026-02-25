package com.pgh.huutinhdoor.service;


import com.pgh.huutinhdoor.dto.request.TicketCreateRequest;
import com.pgh.huutinhdoor.dto.request.TicketItemCreateRequest;
import com.pgh.huutinhdoor.dto.request.TicketItemUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TicketItemResponse;
import com.pgh.huutinhdoor.dto.response.admin.TicketResponse;
import com.pgh.huutinhdoor.entity.*;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import com.pgh.huutinhdoor.enums.PricingType;
import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.TicketItemMapper;
import com.pgh.huutinhdoor.mapper.TicketMapper;
import com.pgh.huutinhdoor.repository.TicketItemRepository;
import com.pgh.huutinhdoor.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private  final TicketRepository ticketRepository;
    private final TicketItemRepository ticketItemRepository;

    private final CustomerService customerService;
    private final ProductService productService;
    private final PricingService pricingService;

    private  final TicketMapper ticketMapper;
    private  final TicketItemMapper ticketItemMapper;

    public void recalculateTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));

        BigDecimal total = pricingService.calculateTicketTotal(ticket);
        ticket.setTotalAmount(total);
        ticketRepository.save(ticket);
    }


    @Transactional(readOnly = true)
    public List<TicketResponse> getAll(){
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TicketItemResponse getItem(Long ticketId, Long itemId) {

        TicketItem item = ticketItemRepository
                .findByIdAndTicketId(itemId, ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));
        return ticketItemMapper.toResponse(item);
    }

    @Transactional(readOnly = true)
    public TicketResponse findByIdOrThrow(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()
                -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));
        return  ticketMapper.toResponse(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()
                -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));
        return ticket;
    }

    @Transactional
    public TicketResponse create(TicketCreateRequest request) {

        Ticket ticket = ticketMapper.toEntity(request);
        Customer customer = customerService.findByIdOrThrow(request.getCustomerId());
        ticket.setCustomer(customer);

        if (request.getItems() != null && !request.getItems().isEmpty()) {
            for (TicketItemCreateRequest itemReq : request.getItems()) {
                addItemForTicket(itemReq, ticket);
            }
        }
        ticketRepository.save(ticket);
        return ticketMapper.toResponse(ticket);
    }


    private Long addItemForTicket(TicketItemCreateRequest request, Ticket ticket) {
        Product product = productService.findByIdOrThrow(request.getProductId());

        TicketItem item = ticketItemMapper.toEntity(request);

        item.setProduct(product);
        ticket.addItem(item);
        return item.getId();
    }

    @Transactional
    public void updateItem(Long ticketId, Long itemId, TicketItemUpdateRequest request) {

        TicketItem item = ticketItemRepository
                .findByIdAndTicketId(itemId, ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        Product product = productService.findByIdOrThrow(request.getProductId());
        item.setProduct(product);
    }

    @Transactional
    public Long addItem(Long ticketId, TicketItemCreateRequest request) {
        Ticket ticket = this.findById(ticketId);
        return addItemForTicket(request, ticket);
    }



    @Transactional
    public void removeItem(Long ticketId, Long itemId) {
        Ticket ticket = this.findById(ticketId);

        TicketItem item = ticket.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        ticket.removeItem(item);
    }

    @Transactional
    public void removeItems(Long ticketId, List<Long> itemIds) {
        Ticket ticket = this.findById(ticketId);

        List<TicketItem> itemsToRemove = ticket.getItems().stream()
                .filter(item -> itemIds.contains(item.getId()))
                .toList();

        for (TicketItem item : itemsToRemove) {
            ticket.removeItem(item);
        }
    }

    @Transactional
    public void delete(Long ticketId){
        if (!ticketRepository.existsById(ticketId)) {
            throw new ResourceNotFoundException("Ticket not found with id: " + ticketId);
        }
        ticketRepository.deleteById(ticketId);
    }
}
