package dev.cinefy.mappers;

import dev.cinefy.controllers.request.StreamingRequest;
import dev.cinefy.controllers.response.StreamingResponse;
import dev.cinefy.entities.Category;
import dev.cinefy.entities.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper{
    public static Streaming toCategory(StreamingRequest request){
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
