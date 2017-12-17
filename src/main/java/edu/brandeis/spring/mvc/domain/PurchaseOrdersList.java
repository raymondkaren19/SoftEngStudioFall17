package edu.brandeis.spring.mvc.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseOrdersList  implements Serializable {

	private List<PurchaseOrders> purchaseOrders;

	public List<PurchaseOrders> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrders> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	@Override
	public String toString() {
		return "PurchaseOrdersList [purchaseOrders=" + purchaseOrders + "]";
	}

	
	
}
