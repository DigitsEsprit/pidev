package entities;

public enum Role {
asset_manager{
	@Override
	public String toString() {
		return "asset_manager";
	}
},
investor{
	@Override
	public String toString() {
		return "investor";
	}
},
broker{
	@Override
	public String toString() {
		return "broker";
	}
},admin{
	@Override
	public String toString() {
		return "admin";
	}
}

}
