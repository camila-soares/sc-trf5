package com.trf5.jus.br.sgc.service;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import java.net.URL;

public interface SeiService extends Service {

    public String getSeiPortServiceAddress();

    public SeiPortType getSeiPortService() throws ServiceException;

    public SeiPortType getSeiPortService(URL portAddress) throws ServiceException;
}
