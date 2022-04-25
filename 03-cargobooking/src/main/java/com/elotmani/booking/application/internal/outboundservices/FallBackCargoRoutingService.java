package com.elotmani.booking.application.internal.outboundservices;

import org.springframework.stereotype.Service;

import com.elotmani.shareddomain.model.TransitPath;

@Service
public class FallBackCargoRoutingService implements CargoRoutingService {

	@Override
	public TransitPath findOptimalRoute(String originUnlockCode, String destinationUnlockCode, String deadLine) {
		// TODO Auto-generated method stub
		return new TransitPath();
	}

}
