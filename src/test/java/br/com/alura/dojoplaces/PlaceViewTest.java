package br.com.alura.dojoplaces;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static br.com.alura.dojoplaces.PlaceBuilder.aPlace;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

class PlaceViewTest {

    @Nested
    @DisplayName("CreatedAt formatted: ")
    class GetFormattedCreatedAt {

        @ParameterizedTest
        @CsvSource({
                "2022-02-16T00:00:00, 16/02/2022",
                "2022-02-16T12:00:00, 16/02/2022",
                "2022-01-31T00:00:00, 31/01/2022",
                "2000-05-15T23:59:59, 15/05/2000"
        })
        @DisplayName("should return createdAt at dd/MM/yyyy format")
        void should_return_created_at_at_dd_mm_yyyy_format(LocalDateTime createdAt, String expectedFormattedCreatedAt) {
            Place place = aPlace().createdAt(createdAt).build();
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getFormattedCreatedAt()).isEqualTo(expectedFormattedCreatedAt);
        }
    }

    @Nested
    @DisplayName("Get days since last update: ")
    class GetDaysSinceLastUpdate {
        @Test
        @DisplayName("should return dash (-) if place was never updated")
        void should_return_dash_if_place_was_never_updated() {
            Place place = aPlace().build();
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getDaysSinceLastUpdate()).isEqualTo("-");
        }

        @Test
        @DisplayName("should return 'Hoje' if place was updated today")
        void should_return_hoje_if_place_was_updated_today() {
            Place place = aPlace().updatedAt(now()).build();
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getDaysSinceLastUpdate()).isEqualTo("Hoje");
        }

        @Test
        @DisplayName("should return '1 dia atras' if place was updated yesterday")
        void should_return_1_dia_atras_if_place_was_updated_yesterday() {
            Place place = aPlace().updatedAt(now().minusDays(1)).build();
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getDaysSinceLastUpdate()).isEqualTo("1 dia atrás");
        }

        @ParameterizedTest
        @ValueSource(longs = {2, 10, 30, 40})
        @DisplayName("should return 'x dias atras' if place was updated before yesterday")
        void should_return_x_dias_atras_if_place_was_updated_before_yesterday(long daysAgo) {
            Place place = aPlace().updatedAt(now().minusDays(daysAgo)).build();
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getDaysSinceLastUpdate()).isEqualTo(daysAgo + " dias atrás");
        }
    }

}