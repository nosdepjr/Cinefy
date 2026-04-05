package dev.cinefy.services;

import dev.cinefy.controllers.request.StreamingRequest;
import dev.cinefy.controllers.response.StreamingResponse;
import dev.cinefy.entities.Streaming;
import dev.cinefy.mappers.StreamingMapper;
import dev.cinefy.repositories.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamingService{
    private final StreamingRepository streamingRepository;

    public StreamingResponse createStreaming(StreamingRequest request){
        return StreamingMapper.toStreamingResponse(streamingRepository.save(StreamingMapper.toCategory((request))));
    }

    public List<StreamingResponse> findAllStreamings(){
        List<Streaming> streamings = streamingRepository.findAll();
        return streamings
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public StreamingResponse findStreamingById(Long id){
        Streaming streaming = streamingRepository
                .findById(id)
                .orElse(null);
        return streaming != null? StreamingMapper.toStreamingResponse(streaming):null;

    }

    public StreamingResponse updateStreaming(Long id, StreamingRequest request) {
        Streaming streaming = streamingRepository.findById(id).orElse(null);

        if (streaming == null) {
            return null;
        }

        if (request.name() != null) {
            streaming.setName(request.name());
        }

        Streaming updatedCategory = streamingRepository.save(streaming);

        return StreamingMapper.toStreamingResponse(updatedCategory);
    }

    public void deleteStreamingById(Long id){
        streamingRepository.deleteById(id);
    }
}