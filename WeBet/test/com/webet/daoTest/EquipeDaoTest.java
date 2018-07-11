package com.webet.daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webet.dao.IEquipeJpaRepository;
import com.webet.entities.Equipe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.webet.config.AppConfig.class })
public class EquipeDaoTest {

    @Autowired
    private IEquipeJpaRepository repo;

    @Autowired
    protected ApplicationContext context;

    // @Test
    // public void testGetId() {
    // Client client = new Client();
    // Integer id = client.getId();
    // assertNull(id);
    //
    // }

    @Test
    public void testCreate() {

	Equipe equipe = new Equipe();
	equipe.setNom("EquipeTest");
	repo.save(equipe);

    }

}
