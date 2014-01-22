package at.jku.ce.stockexchange.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.7
 * 2014-01-21T21:27:43.841+01:00
 * Generated source version: 2.3.7
 * 
 */
@WebService(targetNamespace = "http://service.stockexchange.ce.jku.at/", name = "ExchangeService")
@XmlSeeAlso({ObjectFactory.class})
public interface ExchangeService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "buyStock", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.BuyStock")
    @WebMethod(action = "urn:BuyStock")
    @ResponseWrapper(localName = "buyStockResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.BuyStockResponse")
    public at.jku.ce.stockexchange.service.Exchange buyStock(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getExchanges", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetExchanges")
    @WebMethod(action = "urn:GetExchanges")
    @ResponseWrapper(localName = "getExchangesResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetExchangesResponse")
    public java.util.List<at.jku.ce.stockexchange.service.Exchange> getExchanges();

    @RequestWrapper(localName = "sellStock", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.SellStock")
    @WebMethod(action = "urn:SellStock")
    @ResponseWrapper(localName = "sellStockResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.SellStockResponse")
    public void sellStock(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getStock", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetStock")
    @WebMethod(action = "urn:GetStock")
    @ResponseWrapper(localName = "getStockResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetStockResponse")
    public at.jku.ce.stockexchange.service.Stock getStock(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getTradedStocks", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetTradedStocks")
    @WebMethod(action = "urn:GetTradedStocks")
    @ResponseWrapper(localName = "getTradedStocksResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.GetTradedStocksResponse")
    public java.util.List<at.jku.ce.stockexchange.service.Stock> getTradedStocks();

    @RequestWrapper(localName = "reset", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.Reset")
    @WebMethod(action = "urn:Reset")
    @ResponseWrapper(localName = "resetResponse", targetNamespace = "http://service.stockexchange.ce.jku.at/", className = "at.jku.ce.stockexchange.service.ResetResponse")
    public void reset();
}
