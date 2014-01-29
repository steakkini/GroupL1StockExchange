package at.jku.ce.brokerplatform.view;

import javax.servlet.ServletRequest;

public interface IBrokerPlatformView {
   // public final ServletRequest request;  

    public void displayResult(int x, int y, int result);
    public void displaySuccess(String message);

}
