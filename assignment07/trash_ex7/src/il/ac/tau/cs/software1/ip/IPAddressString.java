package il.ac.tau.cs.software1.ip;

public class IPAddressString implements IPAddress {
	private String address;
	
	IPAddressString(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return address;
	}

	@Override
	public boolean equals(IPAddress other) {
		return address.equals(other.toString());
	}

	@Override
	public int getOctet(int index) {
		String[] s = address.split("\\.");
		return Integer.parseInt(s[index]);
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
