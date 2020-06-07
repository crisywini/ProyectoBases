package co.uniquindio.bases.supermarket.SuperMarketCampestre;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private AdministratorDelegate admin = new AdministratorDelegate();

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
	public void addEmployeeTest() {
		try {
			admin.addEmployee("03", "Jacinto", "Alejandro", "ja3@mail.com", "por la 19", 1);
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void addContractTest() {
		try {
			admin.addContract(900000, "2017-06-03", "2030-06-03", 1, "03", 1);
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void addContractTypeTest() {
		try {
			admin.addContractType("Para toda la vida", "Indefinido");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void addJobTest() {
		try {
			admin.addJob("Robot");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void addPaymentTypeTest() {
		try {
			admin.addPaymentType("Debito", "Pagar con tarjeta que tenga una cuenta debito");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void addPhoneNumberTest() {
		try {
			String numero = "3147571154";
			long numer = Long.parseLong(numero);
			admin.addPhoneNumber(numer, "03");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}
	@Test
	public void addProviderTest() {
		try {
			admin.addProvider("prove2@mail.com", "ELVIVE", "por la 14", "3156468897");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}
	@Test
	public void addServiceTest() {
		try {
			admin.addService("Recibos de cualquier indole", "Servicio luz");
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}
}
