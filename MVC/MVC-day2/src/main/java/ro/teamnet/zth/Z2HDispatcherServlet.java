package ro.teamnet.zth;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import ro.teamnet.zth.fmk.MethodAttributes;
import ro.teamnet.zth.fmk.domain.HttpMethod;
import ro.teamnet.zth.utils.BeanDeserializator;
import ro.teamnet.zth.utils.ControllerScanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Z2HDispatcherServlet extends HttpServlet {

    private ControllerScanner scanner;

    @Override
    public void init() throws ServletException {
        scanner = new ControllerScanner("ro.teamnet.zth.appl.controller");
        scanner.scan();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.POST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.DELETE);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.PUT);
    }

    private void dispatchReply(HttpServletRequest req, HttpServletResponse resp, HttpMethod methodType) {
        try {
            Object resultToDisplay = dispatch(req, methodType);
            reply(resp, resultToDisplay);
        } catch (Exception e) {
            try {
                sendExceptionError(e, resp);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void sendExceptionError(Exception e, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(e.getMessage());
    }

    private void reply(HttpServletResponse resp, Object resultToDisplay) {
        //Todo serialize the return of the output into json using objectmapper (jackson)
        // resultToDisplay = controllerinstance.invokemethod(parameters)
        ObjectMapper objectMapper = new ObjectMapper();

        final String responseAsString;
        try {
            responseAsString = objectMapper.writeValueAsString(resultToDisplay);
            resp.getWriter().print(responseAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object dispatch(HttpServletRequest req, HttpMethod methodType) {

        String uri = req.getPathInfo();
        System.out.println(uri);
        MethodAttributes method = scanner.getMetaData(uri, methodType);
        method.getMethod().getParameters();

        Object[] params = BeanDeserializator.getMethodParams(method.getMethod(), req).toArray();
        try {
            Object controller = method.getControllerClass().newInstance();
            return method.getMethod().invoke(controller,params);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
        }

        return null;
    }


}
