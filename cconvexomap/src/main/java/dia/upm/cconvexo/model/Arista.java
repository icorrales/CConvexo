package dia.upm.cconvexo.model;

public class Arista {
	
	private Punto origen = null;
	private Punto destino = null;
	
	public Arista ()
	{
		
	}

	public Arista(Punto a, Punto b) {
		// TODO Auto-generated constructor stub
		assert a != null;
		assert b != null;
		
		this.origen = a;
		this.destino = b;
	}

	public Punto getOrigen() {
		return origen;
	}

	public void setOrigen(Punto origen) {
		this.origen = origen;
	}

	public Punto getDestino() {
		return destino;
	}

	public void setDestino(Punto destino) {
		this.destino = destino;
	}
	
	@Override
	public String toString()
	{
		return "A1: "+origen.toString()+" A2: "+ destino.toString();
		
	}
	
	@Override
	public boolean equals(Object o)
	{
		assert o != null;
		assert o instanceof Arista;
		
		Arista a = (Arista) o;
		return a.getOrigen().equals(this.origen) && a.getDestino().equals(this.destino) ;
		
	}
	


}
