package mx.com.capacitacionhospital.hospital;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import mx.com.capacitacionhospital.hospital.external.rest.controller.HospitalController;
import mx.com.capacitacionhospital.hospital.external.rest.dto.HospitalDto;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestTransaction
public class HospitalControllerTest {

    @Inject
    HospitalController hospitalController;


    @Test
    public void get_200(){
        var hospitalReponse = hospitalController.get(5);
        assertEquals(Response.Status.OK.getStatusCode(), hospitalReponse.getStatus());
    }

    @Test
    public void get_404(){
        var hospitalReponse = hospitalController.get(1);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), hospitalReponse.getStatus());
    }

    @Test
    public void create_200() {

        var hospital = hospitalController.create(HospitalDto.builder()
                .nombre("Unidad Medicinal No 3")
                .activo(true)
                .build());

        assertEquals(Response.Status.OK.getStatusCode(), hospital.getStatus());
    }



}
