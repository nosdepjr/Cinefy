package dev.cinefy.controllers;

import dev.cinefy.controllers.request.StreamingRequest;
import dev.cinefy.controllers.response.StreamingResponse;
import dev.cinefy.services.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/streamings")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> createStreaming(@RequestBody StreamingRequest request) {

        StreamingResponse response = streamingService.createStreaming(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAllCategories() {
        return ResponseEntity.ok(streamingService.findAllStreamings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findCategoryById(@PathVariable Long id) {
        StreamingResponse response = streamingService.findStreamingById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateCategory(
            @PathVariable Long id,
            @RequestBody StreamingRequest request) {

        StreamingResponse response = streamingService.updateStreaming(id, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        streamingService.deleteStreamingById(id);
        return ResponseEntity.noContent().build();
    }
}
