package com.testapp.udistrital.climbapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by alejandro on 6/06/16.
 */
public class CargaDatosWS {
    String NAMESPACE = "http://www.webserviceX.NET";
    String URL="http://www.webservicex.net/globalweather.asmx";
    String METHOD_NAME = "GetWeather";
    String SOAP_ACTION = "http://www.webserviceX.NET/GetWeather";


    public String getClima(String ciudad, String pais){
        String res = null;

        try{
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("CityName", ciudad);
            request.addProperty("CountryName", pais);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        //test
        //envelope.implicitTypes =true;

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);


            androidHttpTransport.debug = true;

            androidHttpTransport.call(SOAP_ACTION, envelope);
            res = envelope.getResponse().toString();
        }catch (Exception e){
            System.out.println(e.getMessage());
            res=e.getMessage();
        }

        return res;
    }
}
