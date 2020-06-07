package co.uniquindio.bases.supermarket.SuperMarketCampestre;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;

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
			admin.addContract(900000, "2009-06-03", "2010-06-03", 1, "03", 1);
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
			admin.addProvider("prove9@gmail.com", "ELVIVE", "por la 14", "3156468897");
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

	@Test
	public void addProductTest() {
		try {
			admin.addProduct(50, "Bananos", "Bananos de la finca de Mateo", 3200);
			assertTrue(true);
		} catch (EntityRepeatedException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

//	@Test

	@Test
	public void removeContractTest() {
		try {
			admin.removeContract(2);
			assertTrue(true);
		} catch (NonexistentEntityException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

//	@Test

	@Test
	public void removeEmployeeTest() {
		try {
			admin.removeEmployee("03");
			assertTrue(true);
		} catch (NonexistentEntityException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

//	@Test

	@Test
	public void removeProductTest() {
		try {
			admin.removeProduct(1);
			assertTrue(true);
		} catch (NonexistentEntityException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

//	@Test

	@Test
	public void removeProviderTest() {
		try {
			admin.removeProvider(1);
			assertTrue(true);
		} catch (NonexistentEntityException e) {
			System.err.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void updateProductTest() {
		Product product = new Product(16, 6, "Leche de cerdo", " de la finca de don Agustin", 7500);
		admin.updateProduct(product);
		assertTrue(true);
	}

	@Test
	public void getContractListTest() {
		admin.getProvidersGmail();
	}

//	-----------------------

	@Test
	public void getClientOrder() {
		admin.getClientNameDesc();
	}

	@Test
	public void getNameJobQuantityEmployees() {
		admin.getNameJobQuantityEmployees();
	}

	@Test
	public void getQuantityOrderByAddress() {
		admin.getQuantityOrderByAddress();
	}

	@Test
	public void get5OrderByCost() {
		admin.get5OrderByCost();
	}

	@Test
	public void getContractByJob() {
		admin.getContractByJob();
	}

	@Test
	public void getEmployeeBySale() {
		admin.getEmployeeBySale();
	}

	@Test
	public void getEmployeeByPhoneNumber() {
		admin.getEmployeeByPhoneNumber();
	}

	@Test
	public void getQuantityOrderByEmployee() {
		admin.getQuantityOrderByEmployee();
	}

}
