package Game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarteTest {

    @Test
    void testCreationCarte() {
        Carte carte = new Carte(42, 3);
        assertEquals(42, carte.getNumero());
        assertEquals(3, carte.getTetesDeTaureau());
    }
}
