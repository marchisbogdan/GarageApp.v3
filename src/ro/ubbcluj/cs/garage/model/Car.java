package ro.ubbcluj.cs.garage.model;

public class Car {
	private int id;
	private String model;
	private String type;
	private boolean functional;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (functional ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	public Car(int id, String model, String type, boolean functional) {
		super();
		this.id = id;
		this.model = model;
		this.type = type;
		this.functional = functional;
	}
	public Car() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (functional != other.functional)
			return false;
		if (id != other.id)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isFunctional() {
		return functional;
	}
	public void setFunctional(boolean functional) {
		this.functional = functional;
	}
}
