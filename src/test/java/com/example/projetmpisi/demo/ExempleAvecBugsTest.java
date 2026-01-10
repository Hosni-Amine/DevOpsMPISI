package com.example.projetmpisi.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExempleAvecBugsTest {

    private ExempleAvecBugs exempleAvecBugs;

    @BeforeEach
    void setUp() {
        exempleAvecBugs = new ExempleAvecBugs();
    }

    @Test
    void testGetNomComplet() {
        String result = exempleAvecBugs.getNomComplet("Amine", "Hosni");
        assertEquals("AMINE HOSNI", result);
    }

    @Test
    void testDiviser() {
        int result = exempleAvecBugs.diviser(10, 2);
        assertEquals(5, result);
    }

    @Test
    void testDiviserParUn() {
        int result = exempleAvecBugs.diviser(5, 1);
        assertEquals(5, result);
    }

    @Test
    void testLireFichier() {
        // Test que la méthode s'exécute sans exception pour un chemin valide
        assertDoesNotThrow(() -> {
            exempleAvecBugs.lireFichier("test.txt");
        });
    }

    @Test
    void testEvaluerNote_Excellent() {
        String result = exempleAvecBugs.evaluerNote(95);
        assertEquals("Excellent", result);
    }

    @Test
    void testEvaluerNote_TresBien() {
        String result = exempleAvecBugs.evaluerNote(85);
        assertEquals("Très bien", result);
    }

    @Test
    void testEvaluerNote_Bien() {
        String result = exempleAvecBugs.evaluerNote(75);
        assertEquals("Bien", result);
    }

    @Test
    void testEvaluerNote_AssezBien() {
        String result = exempleAvecBugs.evaluerNote(65);
        assertEquals("Assez bien", result);
    }

    @Test
    void testEvaluerNote_Passable() {
        String result = exempleAvecBugs.evaluerNote(55);
        assertEquals("Passable", result);
    }

    @Test
    void testEvaluerNote_Insuffisant() {
        String result = exempleAvecBugs.evaluerNote(40);
        assertEquals("Insuffisant", result);
    }

    @Test
    void testEvaluerNote_Exactement90() {
        String result = exempleAvecBugs.evaluerNote(90);
        assertEquals("Excellent", result);
    }

    @Test
    void testEvaluerNote_Exactement80() {
        String result = exempleAvecBugs.evaluerNote(80);
        assertEquals("Très bien", result);
    }

    @Test
    void testEvaluerNote_Exactement70() {
        String result = exempleAvecBugs.evaluerNote(70);
        assertEquals("Bien", result);
    }

    @Test
    void testEvaluerNote_Exactement60() {
        String result = exempleAvecBugs.evaluerNote(60);
        assertEquals("Assez bien", result);
    }

    @Test
    void testEvaluerNote_Exactement50() {
        String result = exempleAvecBugs.evaluerNote(50);
        assertEquals("Passable", result);
    }

    @Test
    void testMethode1() {
        assertDoesNotThrow(() -> {
            exempleAvecBugs.methode1();
        });
    }

    @Test
    void testMethode2() {
        assertDoesNotThrow(() -> {
            exempleAvecBugs.methode2();
        });
    }

    @Test
    void testRechercherUtilisateur() {
        assertDoesNotThrow(() -> {
            exempleAvecBugs.rechercherUtilisateur("Amine");
        });
    }
}

