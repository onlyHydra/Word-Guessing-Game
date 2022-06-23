package rpcprotocol;


import domain.DTO;
import domain.Joc;
import domain.Jucator;
import dto.*;
import service.Observer;
import service.Service;
import service.TalentShowException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientRpcWorker implements Runnable, Observer {

    private int runda;
    private Joc joc;

    private Service server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcWorker(Service server, Socket connection) {
        runda = 1;
        this.server = server;
        this.connection = connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

    private static Response okResponse=new Response.Builder().type(ResponseType.OK).build();




    //De modificat
    private Response handleRequest(Request request){
        Response response=null;
        if (request.type()== RequestType.LOGIN){
            System.out.println("Login request ..."+request.type() + request.data().toString());
            JucatorDTO jdto= (JucatorDTO) request.data();
            Jucator user= DTOUtils.getFromDTO(jdto);
            try {
                server.login(user, this);


                return okResponse;
            } catch (TalentShowException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type()== RequestType.GET_Jucator){
            System.out.println("GetJucator Request ...");
            JucatorDTO udto=(JucatorDTO) request.data();
            try {
                Jucator jucator=server.findOneJucatorByCredentials(udto.getAlias());
                JucatorDTO jucatorDTO= DTOUtils.getDTO(jucator);
                return new Response.Builder().type(ResponseType.OK).data(jucatorDTO).build();
            } catch (TalentShowException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.JOC_START){
            System.out.println("Joc Inceput");
            try{
                 joc = server.getJoc();
                JocDTO jocDTO = new JocDTO(joc.getCuvant1(),joc.getCuvant2(),joc.getCuvant3(),joc.getLitera1(),joc.getLitera2(),joc.getLitera3());
                return new Response.Builder().type(ResponseType.OK).data(jocDTO).build();
            }catch (TalentShowException e){
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.ROUNDA_NOUA){

                Integer punctaj = 0;
                 RundaDTO rundaDTO =  (RundaDTO) request.data();
                //conditie castig Joc
                if(rundaDTO.verificaCastig()== true){
                    punctaj = rundaDTO.getPunctajCastig();
                    RezultatDTO rezultatDTO = new RezultatDTO(punctaj,true, rundaDTO.getCuvant_introdus());
                    return new Response.Builder().type(ResponseType.JOC_CASTIGAT).data(rezultatDTO).build();
                }
                else{

                    if(runda < 2){


                        // logica punctaj pe runda
                        punctaj = rundaDTO.calculeazaPunctaj();
                        RezultatDTO rezultatDTO = new RezultatDTO(punctaj,false, rundaDTO.getCuvant_introdus());
                        runda+=1;
                        return new Response.Builder().type(ResponseType.JOC_CONTINUE).data(rezultatDTO).build();

                    }
                    else {
                        punctaj = rundaDTO.calculeazaPunctaj();
                        RezultatDTO rezultatDTO = new RezultatDTO(punctaj,false, rundaDTO.getCuvant_introdus());
                        return new Response.Builder().type(ResponseType.JOC_PIERDUT).data(rezultatDTO).build();
                    }
                }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }


}
