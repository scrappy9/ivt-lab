package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  // class to be tested
  private GT4500 ship;

  // dependencies (will be mocked)
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){    
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);

    // object to be tested
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(anyInt())).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(anyInt());
    verify(secondary, times(0)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(anyInt())).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(anyInt())).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(anyInt());
    verify(secondary, times(0)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_PrimaryEmpty() {
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(anyInt())).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(anyInt())).thenReturn(true);

    // Act
    boolean result_a = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result_b = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result_a);
    assertEquals(true, result_b);
    verify(primary, times(0)).fire(anyInt());
    verify(secondary, times(2)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_SecondaryEmpty() {
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(anyInt())).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(anyInt())).thenReturn(false);

    // Act
    boolean result_a = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result_b = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result_a);
    assertEquals(true, result_b);
    verify(primary, times(2)).fire(anyInt());
    verify(secondary, times(0)).fire(anyInt());
  }

  @Test
  public void fireTorpedoALL_PrimaryEmpty() {
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(anyInt())).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(anyInt())).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(anyInt());
    verify(secondary, times(1)).fire(anyInt());
  }

}
