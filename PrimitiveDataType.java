class PrimitiveDataType 
{
	public static void main (String[] args)
	{
		byte min = -128; //2^7
		byte max = 127;  //2^7-1
		
		System.out.printf("Bytes \tmin:%d \t\t\tmax:%d\n", min, max);
		
		short smin = -32768; //2^15
		short smax = 32767; //2^15 -1
		
		System.out.printf("Shorts \tmin:%d \t\t\tmax:%d\n", smin, smax);
		
		int imin = -2147483648; //(2^31)
		int imax = 2147483647; //(2^31 - 1)
		
		System.out.printf("Integer min:%d \t\tmax:%d\n", imin, imax);
		
		long lmin = -9223372036854775808L; //(2^63)  Se tiene que agregar la L para que se impríma
		long lmax = 9223372036854775807L; //(2^63 - 1)
		
		System.out.printf("Long \tmin:%d \tmax:%d\n", lmin, lmax);
		
		float x = 2.123456789123456789123456789f;
		
		System.out.printf("Float \t%.32f\n", x);  // -->>> imprime: Float 2.12345671653747560000000000000000
		
		double y = 2.123456789123456789123456789;
		System.out.printf("Double \t%.32f\n", y); // -->>> imprime: Float 2.12345678912345700000000000000000

		char A = 65;
		
		System.out.printf("Char \tA: %c, Z: %c \n", A, A+25);
		
		boolean F = false;
		boolean T = true;
		System.out.printf("Bolean \tF: %b, T: %b ", F, T);
		System.out.println();

	}
}