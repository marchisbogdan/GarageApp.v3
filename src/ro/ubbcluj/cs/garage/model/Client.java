package ro.ubbcluj.cs.garage.model;

import java.util.ArrayList;

public class Client {

	private int id;
	private String name;
	private Date date;
	private ArrayList<Car> cars;
	private String password;

	/////////////// nested class Address////////////////////

	public Client(int id, String name, int day, int month, int year, ArrayList<Car> cars, String password) {
		super();
		this.id = id;
		this.name = name;
		this.date.setDay(day);
		this.date.setMonth(month);
		this.date.setYear(year);
		this.cars = cars;
		this.password = password;
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public class Date {
		// Attributes
		private int day;
		private int month;
		private int year;

		// Methods
		@Override
		public String toString() {
			return "Address [day=" + day + ", month=" + month + ", year=" + year + "]";
		}

		public Date(int day, int month, int year) {
			super();
			this.day = day;
			this.month = month;
			this.year = year;
		}

		public Date() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + day;
			result = prime * result + month;
			result = prime * result + year;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Date other = (Date) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (day != other.day)
				return false;
			if (month != other.month)
				return false;
			if (year != other.year)
				return false;
			return true;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		private Client getOuterType() {
			return Client.this;
		}

	}
	////////////////////////////////////////////////////////

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Car> getMasini() {
		return cars;
	}

	public void setMasini(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date address) {
		this.date.setDay(address.getDay());
		this.date.setMonth(address.getMonth());
		this.date.setYear(address.getYear());

	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + date + ", cars=" + cars + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
