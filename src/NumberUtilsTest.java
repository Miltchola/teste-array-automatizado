import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    // =============================================
    // Etapa 1: Testes de Entrada Nula ou Vazia
    // =============================================
    
    @Test
    @DisplayName("Ambas as listas nulas deve retornar null")
    void bothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    @Test
    @DisplayName("Lista left nula deve retornar null")
    void leftNull() {
        assertNull(NumberUtils.add(null, List.of(1)));
    }

    @Test
    @DisplayName("Lista right nula deve retornar null")
    void rightNull() {
        assertNull(NumberUtils.add(List.of(1), null));
    }

    @Test
    @DisplayName("Ambas as listas vazias deve retornar lista vazia")
    void bothEmpty() {
        assertEquals(List.of(), NumberUtils.add(List.of(), List.of()));
    }

    @Test
    @DisplayName("Lista left vazia deve retornar right")
    void leftEmpty() {
        assertEquals(List.of(1, 2), NumberUtils.add(List.of(), List.of(1, 2)));
    }

    @Test
    @DisplayName("Lista right vazia deve retornar left")
    void rightEmpty() {
        assertEquals(List.of(3, 4), NumberUtils.add(List.of(3, 4), List.of()));
    }

    // =============================================
    // Etapa 2: Testes de Dígitos Inválidos
    // =============================================

    @Test
    @DisplayName("Dígito negativo em left deve lançar exceção")
    void invalidDigitInLeft() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(List.of(-1), List.of(1)));
    }

    @Test
    @DisplayName("Dígito maior que 9 em right deve lançar exceção")
    void invalidDigitInRight() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(List.of(1), List.of(10)));
    }

    // =============================================
    // Etapa 3: Testes Básicos de Adição
    // =============================================

    @Test
    @DisplayName("Um dígito sem carry")
    void singleDigitNoCarry() {
        assertEquals(List.of(5), NumberUtils.add(List.of(2), List.of(3)));
    }

    @Test
    @DisplayName("Um dígito com carry")
    void singleDigitWithCarry() {
        assertEquals(List.of(1, 1), NumberUtils.add(List.of(9), List.of(2)));
    }

    @Test
    @DisplayName("Múltiplos dígitos sem carry")
    void multipleDigitsNoCarry() {
        assertEquals(List.of(5, 7), NumberUtils.add(List.of(3, 4), List.of(2, 3)));
    }

    @Test
    @DisplayName("Múltiplos dígitos com carry")
    void multipleDigitsWithCarry() {
        assertEquals(List.of(1, 3, 4), NumberUtils.add(List.of(5, 6), List.of(7, 8)));
    }

    // =============================================
    // Etapa 4: Testes com Tamanhos Diferentes
    // =============================================

    @Test
    @DisplayName("Tamanhos diferentes sem carry")
    void differentLengthsNoCarry() {
        assertEquals(List.of(1, 2, 4), NumberUtils.add(List.of(1, 2), List.of(1, 1, 2)));
    }

    @Test
    @DisplayName("Tamanhos diferentes com carry")
    void differentLengthsWithCarry() {
        assertEquals(List.of(2, 0, 1), NumberUtils.add(List.of(9, 9), List.of(1, 0, 2)));
    }

    // =============================================
    // Etapa 5: Testes de Limite
    // =============================================

    @Test
    @DisplayName("Soma no limite sem gerar carry (8+1)")
    void noCarryGenerated() {
        assertEquals(List.of(9), NumberUtils.add(List.of(8), List.of(1)));
    }

    @Test
    @DisplayName("Soma no limite gerando carry (9+1)")
    void carryGenerated() {
        assertEquals(List.of(1, 0), NumberUtils.add(List.of(9), List.of(1)));
    }

    @Test
    @DisplayName("Carry que propaga para novo dígito")
    void carryPropagatesToNewDigit() {
        assertEquals(List.of(1, 0, 0, 0), NumberUtils.add(List.of(9, 9, 9), List.of(1)));
    }

    // =============================================
    // Etapa 6: Testes de Casos Especiais
    // =============================================

    @Test
    @DisplayName("Múltiplos 9s")
    void multipleNines() {
        assertEquals(List.of(1, 0, 9, 9, 8), 
            NumberUtils.add(List.of(9, 9, 9, 9), List.of(9, 9, 9)));
    }

    @Test
    @DisplayName("Zeros à esquerda")
    void leadingZeros() {
        assertEquals(List.of(1, 2), NumberUtils.add(List.of(0, 0, 1, 2), List.of(0)));
    }

    @Test
    @DisplayName("Números muito grandes")
    void veryLargeNumbers() {
        assertEquals(List.of(1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            NumberUtils.add(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9), List.of(1)));
    }

    @Test
    @DisplayName("Zero no meio do número")
    void zeroInMiddle() {
        assertEquals(List.of(1, 0, 0, 2),
            NumberUtils.add(List.of(9, 9, 1), List.of(0, 0, 1)));
    }

    @Test
    @DisplayName("Todos os dígitos 9")
    void allDigitsNine() {
        assertEquals(List.of(1, 9, 9, 9, 8),
            NumberUtils.add(List.of(9, 9, 9, 9), List.of(9, 9, 9, 9)));
    }

    // =============================================
    // Etapa 7: Testes Adicionais Criativos
    // =============================================

    @Test
    @DisplayName("Soma com carry em todos os dígitos")
    void carryOnAllDigits() {
        assertEquals(List.of(1, 3, 5, 7, 9),
            NumberUtils.add(List.of(6, 7, 8, 9), List.of(6, 7, 8, 9)));
    }

    @Test
    @DisplayName("Números com tamanhos muito diferentes")
    void veryDifferentLengths() {
        assertEquals(List.of(1, 0, 0, 0, 0, 0, 1),
            NumberUtils.add(List.of(1), List.of(1, 0, 0, 0, 0, 0, 0)));
    }

    @Test
    @DisplayName("Soma que resulta em todos zeros exceto o primeiro dígito")
    void allZerosExceptFirst() {
        assertEquals(List.of(1, 0, 0, 0, 0),
            NumberUtils.add(List.of(9, 9, 9, 9), List.of(1)));
    }
}