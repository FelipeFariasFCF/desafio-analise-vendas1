package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}
			
			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 preco medio maior");
			List<Sale> listSale = list.stream()
					.filter(s -> s.getYear() == 2016)
					.sorted((x, y) -> y.averagePrice().compareTo(x.averagePrice()))
					.limit(5)
					.collect(Collectors.toList());
			listSale.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(s -> s.getSeller().equals("Logan"))
					.filter(s -> s.getMonth() == 1 || s.getMonth() == 7)
					.map(s -> s.getTotal()).reduce(0.0, Double::sum);
			System.out.println();
			System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + String.format("%.2f", sum));
				
					
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}