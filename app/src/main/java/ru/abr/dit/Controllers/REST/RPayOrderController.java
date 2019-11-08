package ru.abr.dit.Controllers.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.Models.Entities.Docs.RPayOrder;
import ru.abr.dit.Services.SOAP.UPGDocumentBody.PayDocRuBody;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/rpayorder")
public class RPayOrderController {

    @Autowired
    PayDocRuBody payDocRuBody;

    @PostMapping("/new")
    public String createRPayOrder(@RequestBody RPayOrder doc) {

        UUID rpoId = payDocRuBody.createRPayOrder(doc);

        return rpoId != null ? "{ \"id\": \"" + rpoId.toString() + "\"}" : "";

    }

    @PostMapping("/save")
    public boolean saveRPayOrder(@RequestBody RPayOrder doc){

        return payDocRuBody.updateRPayOrder(doc);

    }

    @GetMapping("/send/{id}")
    public boolean sendRPayorder(@PathVariable String id) throws JAXBException, IOException, SOAPException {

        payDocRuBody.sendPayDocRu(id);


        return false;

    }





}
