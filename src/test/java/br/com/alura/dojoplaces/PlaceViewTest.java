package br.com.alura.dojoplaces;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

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
            Place place = aPlaceCreatedAt(createdAt);
            PlaceView placeView = new PlaceView(place);
            assertThat(placeView.getFormattedCreatedAt()).isEqualTo(expectedFormattedCreatedAt);
        }

        private Place aPlaceCreatedAt(LocalDateTime createdAt) {
            Place place = new Place("place", "Name place", "District", "City");
            ReflectionTestUtils.setField(place, "createdAt", createdAt);
            return place;
        }
    }

    @Nested
    @DisplayName("Get days since last update: ")
    class GetDaysSinceLastUpdate {
        @Test
        @DisplayName("should return dash (-) if place was never updated")
        void should_return_dash_if_place_was_never_updated() {
            Place place = new Place("Code", "Name", "District", "City");
            new PlaceBuilder()
        }

        @Test
        @DisplayName("should return 'Hoje' if place was updated today")
        void should_return_hoje_if_place_was_updated_today() {

        }

        @Test
        @DisplayName("should return '1 dia atras' if place was updated yesterday")
        void should_return_1_dia_atras_if_place_was_updated_yesterday() {

        }

        @Test
        @DisplayName("should return 'x dias atras' if place was updated before yesterday")
        void should_return_x_dias_atras_if_place_was_updated_before_yesterday() {

        }
    }

}