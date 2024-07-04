import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Codigo {

    public Scanner leer = new Scanner(System.in);
    private ArrayList<Modelo> aparatos = new ArrayList<>();

    public Codigo(){
        archivopasaarraylist();
    }

    public void agggprodarray() throws IOException {
        String name;
        int usodario = 0;
        double volteos = 0.0;
        double consumodiario = 0.0;
        double consumomensual = 0.0;

        System.out.println("________________________________________________________________");
        System.out.println("Ingrese el nombre del objeto que desea registrar");
        name = leer.nextLine();

        while (true) {
            try {
                System.out.println("Cuantas horas diarias suele usar su " + name + "?");
                usodario = leer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        }

        while (true) {
            try {
                System.out.println("Revise su producto y digite la potencia de consumo que tiene");
                System.out.println("La cantidad de consumo tiene que ser en Watts (W)");
                volteos = leer.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        }

        consumodiario = (volteos / 1000.0) * usodario;
        consumomensual = consumodiario * 30;

        aparatos.add(new Electrodomesticos(name, usodario, volteos, consumomensual));
        escribirarrayenarchivo(); //Guardado automatico

        System.out.println("________________________________________________________________\n");
    }

    public void escribirarrayenarchivo() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("aparatos.txt"))) {
            for (Modelo aparato : aparatos) {
                bw.write(aparato.toString());
                bw.newLine(); //pequeño buffer
            }
            System.out.println("Archivo guardado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerarraydearchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminararrayyarchivo() throws IOException {
        int opcion = 0;
        if (aparatos.isEmpty()) {
            System.out.println("No hay dispositivos electricos disponibles para eliminar");
            return;
        }

        while (opcion < 1 || opcion > aparatos.size()) {
            do {
                System.out.println("Ingrese el objeto electrico que desea eliminar");
                for (int i = 0; i < aparatos.size(); i++) {
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println((i + 1) + "-" + aparatos.get(i).getName());
                    System.out.println("---------------------------------------------------------------------------");
                }
                try {
                    System.out.print("Elija una opcion: ");
                    opcion = leer.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Opcion Incorrecta !");
                    leer.nextLine();
                }
            } while (true);
        }

        Modelo apa = aparatos.remove(opcion - 1);
        System.out.println("Producto " + apa.getName() + " eliminado correctamente.");
        escribirarrayenarchivo();
    }

    public void archivopasaarraylist() {
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dividir = line.split("---");
                if (dividir.length == 4) {
                    String nombre = dividir[0].trim();
                    int usodiario = Integer.parseInt(dividir[1].trim());
                    double volteos = Double.parseDouble(dividir[2].trim());
                    double consumomensual = Double.parseDouble(dividir[3].trim());
                    aparatos.add(new Electrodomesticos(nombre, usodiario, volteos, consumomensual));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Interfaz() {
        boolean salir = false;
        do {
            try {
                System.out.println("-----BIENVENIDO A ELECTROCOST-----");
                System.out.println("1- Agregar Electrodomestico");
                System.out.println("2- Ver Consumo");
                System.out.println("3- Modificar Electrodomestico");
                System.out.println("4- Eliminar Electrodomestico");
                System.out.println("5- Mostrar lista");
                System.out.println("6- Generar factura");
                System.out.println("7- Salir");
                System.out.print("> Elija una opcion: ");
                int opcion = leer.nextInt();
                System.out.println("________________________________________________________________\n");
                leer.nextLine(); //buffer

                switch (opcion) {
                    case 1:
                        agggprodarray();
                        break;
                    case 2:
                        consumototaldetodoslosaparatos();
                        break;
                    case 3:
                        modificaraparatos();
                        break;
                    case 4:
                        eliminararrayyarchivo();
                        break;
                    case 5:
                        mostraraparatos();
                        break;
                    case 6:
                        MostrarFact();
                        break;
                    case 7:
                        System.out.println("Gracias por usar ELECTROCOST");
                        salir = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        } while (!salir);
    }

    public double calcularconsumodetodoslosaparatos() {
        double consumototal = 0.0;
        for (Modelo aparato : aparatos) {
            consumototal += aparato.getConsumomensual();
        }
        return consumototal;
    }

    public void consumototaldetodoslosaparatos() {
        if (aparatos.isEmpty()) {
            System.out.println("No hay objetos en la lista");
            System.out.println("!Agrega un nuevo dispositivo electrico para poder ver su consumo !");
            System.out.println("________________________________________________________________\n");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("OBJETOS ELECTRICOS - CONSUMO MENSUAL ");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Modelo aparato : aparatos) {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println(aparato.getName() + " <--Su consumo mensual es de --> C$" + aparato.getConsumomensual());
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("-CONSUMO MENSUAL-");
        System.out.println("----------------------------------------------------------------------------------------------------");
        double consumototaldetodoslosaparatos = calcularconsumodetodoslosaparatos();
        System.out.println("El consumo mensual de todos los dispositivos de la lista es de C$" + consumototaldetodoslosaparatos);
        System.out.println("----------------------------------------------------------------------------------------------------\n");
    }

    public void modificaraparatos() throws IOException {
        if (aparatos.isEmpty()) {
            System.out.println("________________________________________________________________");
            System.out.println("No hay aparatos en tu lista");
            System.out.println("!Agrega aparatos electricos para poder modificarlos !");
            System.out.println("________________________________________________________________\n");
            return;
        }

        int opcion = 0;
        int op = 0;
        String namen;

        while (opcion < 1 || opcion > aparatos.size()) {
            do {
                try {
                    System.out.println("________________________________________________________________");
                    System.out.println("Ingrese el indice del objeto que desea modificar");
                    for (int i = 0; i < aparatos.size(); i++) {
                        System.out.println("------------------------------------------------");
                        System.out.println((i + 1) + "-" + aparatos.get(i).getName());
                        System.out.println("------------------------------------------------");
                    }
                    System.out.print("Elija una opcion -->");
                    opcion = leer.nextInt();
                    System.out.println("________________________________________________________________");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
                }
            } while (true);
        }

        while (op < 1 || op > 3) {
            try {
                System.out.println("Ingrese los atributos del objeto que desea modificar");
                System.out.println("1- Nombre");
                System.out.println("2- Uso diario");
                System.out.println("3- Volteos del objeto");
                System.out.print("Elija una opcion -->");
                op = leer.nextInt();
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        }

        leer.nextLine(); // Limpiar el buffer del escáner
        Modelo aparato = aparatos.get(opcion - 1);
        switch (op) {
            case 1:
                System.out.println("________________________________________________________________");
                System.out.println("Ingrese el nuevo nombre del objeto -->");
                namen = leer.nextLine();
                aparato.setName(namen);
                System.out.println("Nombre cambiado correctamente");
                System.out.println("________________________________________________________________\n");
                break;
            case 2:
                System.out.println("________________________________________________________________");
                System.out.println("Ingrese el nuevo valor de uso diario -->");
                int ud = leer.nextInt();
                aparato.setUsodiario(ud);
                aparato.actualizarConsumo();
                System.out.println("Uso diario cambiado correctamente");
                System.out.println("________________________________________________________________\n");
                break;
            case 3:
                System.out.println("________________________________________________________________");
                System.out.println("Ingrese el nuevo valor de consumo en volteos (W) -->");
                double vol = leer.nextDouble();
                aparato.setVolteos(vol);
                aparato.actualizarConsumo();
                System.out.println("Volteos cambiados correctamente");
                System.out.println("________________________________________________________________\n");
                break;
            default:
                break;
        }

        escribirarrayenarchivo();
    }

    public void mostraraparatos() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("OBJETOS ELECTRICOS ");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Modelo aparato : aparatos) {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("Nombre -->" + aparato.getName());
            System.out.println("Uso diario -->" + aparato.getUsodiario());
            System.out.println("Potencia (W) -->" + aparato.getVolteos());
            System.out.println("Consumo mensual -->" + aparato.getConsumomensual());
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
    }

    public void MostrarFact() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Consumo TOTAL de energia");
        double consumototaldetodoslosaparatos = calcularconsumodetodoslosaparatos();
        double calculo = (consumototaldetodoslosaparatos * 4.48);
        double IVA = (calculo * 0.15);
        double Total = (calculo + IVA);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Consumo mensual C$" + consumototaldetodoslosaparatos + " kilowatts hora");
        System.out.println("Costo unitario por kilowatts hora C$4.48");
        System.out.println("Costo sin IVA C$" + calculo);
        System.out.println("IVA 15% --> C$" + IVA);
        System.out.println("TOTAL A PAGAR --> C$" + Total);
        System.out.println("---------------------------------------------------------------------------");
    }
}