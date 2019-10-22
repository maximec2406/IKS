package ru.abr.dit.Models.XMLTemplate.Model;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "models"
})
@XmlRootElement(name = "Request", namespace = "http://bssys.com/upg/request")
public class RequestModelsModel {

    @XmlElement( name = "Models", namespace = "http://bssys.com/upg/request")
    protected Models models;

    @XmlAttribute(name = "requestId", required = true)
    protected String requestId;

    @XmlAttribute(name = "version", required = true)
    protected String version;

    public RequestModelsModel() {
        this.version = "1";
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Models getModels() {
        return models;
    }

    public void setModels(Models models) {
        this.models = models;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "model"
    })
    public static class Models{

        @XmlElement(name = "Model", namespace = "http://bssys.com/upg/request")
        protected String model;

        public Models() {
        }

        public Models(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

    }
}
