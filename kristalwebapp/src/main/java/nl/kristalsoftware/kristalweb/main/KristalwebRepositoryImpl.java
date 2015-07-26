package nl.kristalsoftware.kristalweb.main;

import nl.kristalsoftware.kristalweb.page.PageContentHandler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jcr.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 24-05-15.
 */
@ApplicationScoped
public class KristalwebRepositoryImpl implements BaseRepository {

    @Inject
    private Logger logger;

    @Resource(mappedName="java:/jcr/kristalweb")
    private javax.jcr.Repository repository;

    public KristalwebRepositoryImpl() {}

    @PostConstruct
    public void init() {
        logger.info("init postconstruct in KristalwebRepositoryImpl");
        try {
            Session session = this.createSession();
            Node rootNode = session.getRootNode();
            if (!rootNode.hasNode("site")) {
                logger.info("No site node available, import.....");
                this.importXML(session, "kristalwebpages.xml");
            }
            else {
                logger.info("The site node found");
                this.exportXML(session);
            }
            this.logoutSession(session);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @RequestScoped
    @Produces
    public Session createSession() {
        Session session = null;
        try {
            logger.info("createSession");
            session = repository.login();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return session;
    }

    public void logoutSession(@Disposes final Session session) {
        logger.info("logoutSession");
        session.logout();
    }

    @Override
    public void checkForContent(String nodePath, PageContentHandler pageContentHandler) {
        logger.info("calling checkForContent() method");
        /*
        String customerName = "prima";
        StringBuilder page = new StringBuilder("<html><head><title>KristalCMS</title></head><body>");
        page.append("<h1>Hello ").append(customerName).append("</h1>");
        page.append("<p>").append(nodePath).append("</p>");
        page.append("</body></html>");

        if (!pageContentHandler.pageExists(nodePath)) {
            logger.info("Page " + nodePath + " does not exist");
            pageContentHandler.createPage(nodePath, page.toString());
            Session session = this.createSession();
            exportXML(session);
            logoutSession(session);
        }
        else {
            logger.info("Page " + nodePath + " does exist");
        }
        */
    }

    private void importXML(Session session, String xmlFileName) {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream is = loader.getResourceAsStream(xmlFileName);
        if (is != null) {
            try {
                session.importXML("/", is, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
                session.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("The file " + xmlFileName + " not found.");
        }
    }

    private void exportXML(Session session) {
        try {
            session.exportSystemView("/site", new FileOutputStream(new File("/Volumes/LaCie/tmp_output/kristalweb/site.xml")), false, false);
            //session.exportSystemView("/config", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/config.xml")), false, false);
            //session.exportSystemView("/orders", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/orders.xml")), false, false);
            //session.exportSystemView("/countries", new FileOutputStream(new File("/Users/sjoerd/Documents/tmp/dealers.xml")), false, false);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}
