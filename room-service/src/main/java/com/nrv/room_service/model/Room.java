package com.nrv.room_service.model;

import com.nrv.room_service.model.enums.Availability;
import com.nrv.room_service.model.enums.Type;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entity class for Room. Represents the fields present in Room database. This will
 * be used to map values from database in from of an Object.
 *
 * @author Nirav Parekh
 * @see Type
 * @since 1.0
 */
@Document(collection = "rooms") // Specify the collection name in MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    private String roomId;

    @Indexed(unique = true) // Ensure roomNumber is unique in MongoDB
    private String roomNumber;

    @Field("room_type") // Optional: specify a custom field name
    private Type roomType; // Enum can be used directly

    private Double pricePerNight;
    private Availability availability;
    private String image;

    @Min(value = 0, message = "Floor must be non-negative")
    @Max(value = 30, message = "Floor must be less than or equal to 30")
    private Integer floor;

    private String description;
}
