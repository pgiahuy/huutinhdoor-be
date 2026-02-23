package com.pgh.huutinhdoor.controller.admin;


import com.pgh.huutinhdoor.dto.request.TicketCreateRequest;
import com.pgh.huutinhdoor.dto.request.TicketItemCreateRequest;
import com.pgh.huutinhdoor.dto.request.TicketItemUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TicketItemResponse;
import com.pgh.huutinhdoor.dto.response.admin.TicketResponse;
import com.pgh.huutinhdoor.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/tickets")
public class AdminTicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> result = ticketService.getAll();
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findByIdOrThrow(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketCreateRequest request) {
        TicketResponse result = ticketService.create(request);

        return  ResponseEntity
                .created(URI.create("/api/v1/admin/tickets/" + result.getId()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }


//   =================== ITEM ========================

    @GetMapping("/{id}/items/{itemId}")
    public ResponseEntity<TicketItemResponse> getItem(
            @PathVariable Long id,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(ticketService.getItem(id, itemId));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Void> createTicketItem(
            @PathVariable Long id,
            @RequestBody TicketItemCreateRequest request) {
        Long itemId = ticketService.addItem(id, request);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/tickets/" + id + "/items/" + itemId))
                .build();
    }

    @PutMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> updateTicketItem(
            @PathVariable Long id,
            @PathVariable Long itemId,
            @RequestBody TicketItemUpdateRequest request) {

        ticketService.updateItem(id, itemId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> deleteTicketItem(
            @PathVariable Long id,
            @PathVariable Long itemId) {

        ticketService.removeItem(id, itemId);
        return ResponseEntity.noContent().build();
    }

}
