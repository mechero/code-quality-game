package es.macero.cqgame.modules.users.dao;

import es.macero.cqgame.modules.users.domain.SonarUser;
import es.macero.cqgame.modules.users.domain.SonarUserList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.List;

@Repository
public class SonarUserRepository {

    private SonarUserList list;

    @Value("${userDataFile}")
    private String userDataFile;

    @PostConstruct
    public void init() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SonarUserList.class);
        list = (SonarUserList) context.createUnmarshaller()
                .unmarshal(getClass().getClassLoader().getResourceAsStream("data/" + userDataFile));
    }

    public List<SonarUser> findAll() {
        return list.getUsers();
    }
}
