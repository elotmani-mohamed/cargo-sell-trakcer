package com.elotmani.booking.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elotmani.shareddomain.model.TransitPath;

@FeignClient(name = "routing-service",fallback = FallBackCargoRoutingService.class)
public interface CargoRoutingService {
	
	
	@GetMapping(path = "/cargorouting/optimalRoute")
	TransitPath findOptimalRoute(@RequestParam("origin") String originUnlockCode,
								@RequestParam("destination")	String destinationUnlockCode,
								@RequestParam("deadline")	String deadLine);
	
	

}
