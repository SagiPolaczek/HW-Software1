package il.ac.tau.cs.software1.ip;

public class IPAddressShort implements IPAddress {
	
	private short[] address;
	
	IPAddressShort(short[] address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String res = "";
		res = res + address[0] + "." + address[1] + "." + address[2] + "." + address[3];
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
		return address[index];
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
