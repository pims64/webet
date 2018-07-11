package com.webet.entitiesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.webet.entities.Equipe;

public class EquipeTest {

    @Test
    public void testConstruire() {
	Equipe equipe = new Equipe();
	assertNotNull(equipe);

    }

    @Test
    public void testGetId() {
	Equipe equipe = new Equipe();
	Long id = equipe.getId();
	assertNull(id);

    }

    @Test
    public void testSetId() {
	Equipe equipe = new Equipe();
	equipe.setId(Long.valueOf(1L));
	assertEquals(equipe.getId(), Long.valueOf(1L));

    }

    @Test
    public void testGetNom() {
	Equipe equipe = new Equipe();
	String nom = equipe.getNom();
	assertNull(nom);

    }

    @Test
    public void testSetNom() {
	Equipe equipe = new Equipe();
	equipe.setNom("nom1");
	assertEquals(equipe.getNom(), "nom1");

    }

    @Test
    public void testGetNomImg() {
	Equipe equipe = new Equipe();
	String nomImg = equipe.getNomImg();
	assertNull(nomImg);

    }

    @Test
    public void testSetNomImg() {
	Equipe equipe = new Equipe();
	equipe.setNom("nomImg2");
	assertEquals(equipe.getNom(), "nomImg1");

    }

}
