package rpcprotocol;

import domain.Joc;
import domain.Jucator;
import domain.Runda;
import dto.*;
import service.Observer;
import service.Service;
import service.TalentShowException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServicesRpcProxy implements Service {
    private String host;
    private int port;

    private Observer client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;

    private volatile boolean finished;
    public ServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request) throws TalentShowException {
        try {
            output.writeObject(request);
            output.flush();


        } catch (IOException e) {
            throw new TalentShowException("Error sending object "+e);
        }

    }

    private Response readResponse() throws TalentShowException {
        Response response=null;
        try{

            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void initializeConnection() throws TalentShowException {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    @Override
    public Jucator findOneJucatorByCredentials(String username) {
        initializeConnection();
        JucatorDTO udto= new JucatorDTO(username,-1);
        Request req=new Request.Builder().type(RequestType.GET_Jucator).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            throw new TalentShowException(err);
        }
        JucatorDTO jDTO=(JucatorDTO)response.data();
        Jucator friend= DTOUtils.getFromDTO(jDTO);
        return friend;
    }

    @Override
    public void login(Jucator user, Observer client) {
        initializeConnection();
        JucatorDTO jdto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(jdto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new TalentShowException(err);
        }
    }

    @Override
    /// DE modificat
    public Joc getJoc() {
        initializeConnection();
        Request req = new Request.Builder().type(RequestType.JOC_START).build();
        sendRequest(req);
        Response response = readResponse();

        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new TalentShowException(err);
        }
        JocDTO jocDTO =(JocDTO) response.data();
        Joc joc = new Joc(jocDTO.getCuvant1(),jocDTO.getCuvant2(),jocDTO.getCuvant3(),jocDTO.getLitera1(),jocDTO.getLitera2(),jocDTO.getLitera3());
        joc.setId(jocDTO.getId());
        return joc;
    }


    @Override
    /// DE modificat
    public Runda addRezultat(Runda runda, Joc joc) {

        initializeConnection();
        RundaDTO udto= new RundaDTO(joc.getCuvant1(),joc.getCuvant2(),joc.getCuvant3(),runda.getCuvant_introdus());
        Request req=new Request.Builder().type(RequestType.ROUNDA_NOUA).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        RezultatDTO rezultatDTO = (RezultatDTO) response.data();
        Runda rezultat = new Runda();
        rezultat.setPunctaj(rezultatDTO.getPunctaj());
        rezultat.setCuvant_introdus(rezultat.getCuvant_introdus());
        rezultat.setCastigat(rezultatDTO.isCastigat());
        if (response.type()== ResponseType.JOC_PIERDUT){
            return  rezultat;
        }
        if (response.type()== ResponseType.JOC_CASTIGAT){
            return  rezultat;
        }
        if (response.type()== ResponseType.JOC_CONTINUE){
            return  rezultat;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new TalentShowException(err);
        }
        return  rezultat;
    }



    private void handleUpdate(Response response){
        if (response.type()== ResponseType.ADD){
            DTOUpdate dtoUpdate = (DTOUpdate) response.data();
            try {
                System.out.println("IN HANDLE UPDATE");
            } catch (TalentShowException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isUpdate(Response response){
        return response.type()== ResponseType.ADD;
    }

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{

                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}

