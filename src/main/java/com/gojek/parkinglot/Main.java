package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.executor.ParkingLotExecutorService;
import com.gojek.parkinglot.executor.ParkingLotExecutorServiceImpl;
import com.gojek.parkinglot.service.impl.ParkingLotServiceImpl;
import com.gojek.parkinglot.utils.ErrorCodeEnum;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	ParkingLotExecutorService executor = new ParkingLotExecutorServiceImpl();
		executor.attachService(new ParkingLotServiceImpl());
		BufferedReader bufferReader = null;
		String input = null;
		try
		{
			System.out.println("\n\n\n\n\n");
			System.out.println("===================================================================");
			System.out.println("===================PARKING LOT APP     ====================");
			System.out.println("===================================================================");
			outputUsage();
			switch (args.length)
			{
				case 0: // Interactive: command-line input/output
				{
					System.out.println("Please Enter 'exit' to terminate");
					System.out.println("Input action:");
					while (true)
					{
						try
						{
							bufferReader = new BufferedReader(new InputStreamReader(System.in));
							input = bufferReader.readLine().trim();
							if (input.equalsIgnoreCase("exit"))
							{
								break;
							}
							else
							{
								if (executor.validate(input))
								{
									try
									{
										executor.executeAction(input.trim());
									}
									catch (Exception e)
									{
										System.out.println(e.getMessage());
									}
								}
								else
								{
									outputUsage();
								}
							}
						}
						catch (Exception e)
						{
							throw new GoJekParkingException(ErrorCodeEnum.INVALID_REQUEST.getDescription(), e);
						}
					}
					break;
				}
				case 1:// File input/output
				{
					File inputFile = new File(args[0]);
					try
					{
						bufferReader = new BufferedReader(new FileReader(inputFile));
						int lineNo = 1;
						while ((input = bufferReader.readLine()) != null)
						{
							input = input.trim();
							if (executor.validate(input))
							{
								try
								{
									executor.executeAction(input);
								}
								catch (Exception e)
								{
									System.out.println(e.getMessage());
								}
							}
							else
								System.out.println("Incorrect Command supplied,  Found at line: " + lineNo + " ,Input: " + input);
							lineNo++;
						}
					}
					catch (Exception e)
					{
						throw new GoJekParkingException(ErrorCodeEnum.INVALID_FILE.getDescription(), e);
					}
					break;
				}
				default:
					System.out.println("Invalid input supplied. Please see README file.");
			}
		}
		catch (GoJekParkingException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				if (bufferReader != null)
					bufferReader.close();
			}
			catch (IOException e)
			{
			}
		}
	}
	
	private static void outputUsage()
	{
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append(
				"--------------Please chose from one of the below commands. {variable} to be replaced -----------------------")
				.append("\n");
		buffer = buffer.append("A) Create a parking lot of size n               ---> create_parking_lot {capacity}")
				.append("\n");
		buffer = buffer
				.append("B)  Park a car                                    ---> park <<car_number>> {car_clour}")
				.append("\n");
		buffer = buffer.append("C) Unpark car from parking lot                ---> leave {slot_number}")
				.append("\n");
		buffer = buffer.append("D) Output status of parking slot                     ---> status").append("\n");
		buffer = buffer.append(
				"E) Get cars registration no for the given car color ---> registration_number_by_colour {car_color}")
				.append("\n");
		buffer = buffer.append(
				"F) Get slot numbers for the given car color         ---> slot_number_by_colour {car_color}")
				.append("\n");
		buffer = buffer.append(
				"G) Get slot number for the given car number         ---> slot_number_by_registration_number {car_number}")
				.append("\n");
		System.out.println(buffer.toString());
    }
}
