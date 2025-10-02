import java.io.*;
import java.util.*;

public class AddressBook {
    private HashMap<String, String> contactos;
    private String archivo;

    public AddressBook(String archivo) {
        this.archivo = archivo;
        this.contactos = new HashMap<>();
        load();
    }

    // Cargar contactos desde archivo CSV
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    contactos.put(datos[0], datos[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, se creará uno nuevo al guardar.");
        }
    }

    // Guardar contactos en archivo CSV
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos: " + e.getMessage());
        }
    }

    // Mostrar contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contactos.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Crear contacto
    public void create(String numero, String nombre) {
        if (contactos.containsKey(numero)) {
            System.out.println("El número ya existe en la agenda.");
        } else {
            contactos.put(numero, nombre);
            save();
            System.out.println("Contacto agregado correctamente.");
        }
    }

    // Eliminar contacto
    public void delete(String numero) {
        if (contactos.remove(numero) != null) {
            save();
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un contacto con ese número.");
        }
    }

    // Menú interactivo
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n
