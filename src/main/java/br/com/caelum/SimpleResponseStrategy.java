package br.com.caelum;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

public class SimpleResponseStrategy implements AggregationStrategy {
	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		for (MuleEvent event : context.collectEventsWithExceptions()) {
			throw new RuntimeException(event.toString());
		}
		// criado um payload
		DefaultMuleEvent evento = new DefaultMuleEvent(context.getOriginalEvent(),
				context.getOriginalEvent().getFlowConstruct());
		evento.getMessage().setPayload("<resposta>ok do SimpleResponseStrategy</resposta>");
		return evento;
	}
}