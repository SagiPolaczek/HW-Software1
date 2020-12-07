package il.ac.tau.cs.software1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {
	
//	private String address;
	private int address;
	
	IPAddressInt(int address) {
//		String m = Integer.toBinaryString(address);
//		this.address = String.format("%32s", m).replaceAll(" ", "0");
		this.address = address;
		
	}

	@Override
	public String toString() {
		String res = "";
		res = res + this.getOctet(0) + "." + this.getOctet(1) + "." + this.getOctet(2) + "." + this.getOctet(3);
		return res;
	}

	@Override
	public boolean equals(IPAddress other) {
		for (int i = 0; i < 4; i++) {
			if (this.getOctet(i) != other.getOctet(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getOctet(int index) {
		ByteBuffer bytes = ByteBuffer.allocate(4).putInt(address);
		byte b = bytes.get(index);
		
		return (int)(b & 0xFF);
		
		
	}

	@Override
	public boolean isPrivateNetwork(){
		int a = this.getOctet(0);
		int b = this.getOctet(1);
		if((a == 10) || (a == 172 && b >=16 && b<=31) || (a == 192 && b == 168) || (a == 169 && b == 254)) {
			return true;
		}
		return false;
		
	}
	
}
