package com.rankinteractive.service;

import com.rankinteractive.config.DagacubeLogger;
import com.rankinteractive.exception.InternalServerErrorException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.Customer;
import com.rankinteractive.model.TonicEvent;
import com.rankinteractive.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    public EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @DagacubeLogger
    public List<TonicEvent> findAll() {
        try {
            return eventRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public TonicEvent findById(Long id) {

        try {
            Optional<TonicEvent> optionalTonicEvent = eventRepository.findById(id);

            return optionalTonicEvent.orElse(null);

        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public TonicEvent save(TonicEvent event) {

        try {
            if(event.getId() == null) {
                event.setCreateDate(new Date());
            }
            event.setLastModifiedDate(new Date());

            return eventRepository.save(event);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }

    }

    @Override
    @DagacubeLogger
    public void delete(TonicEvent event){
        try {
            eventRepository.delete(event);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public void deleteById(Long id){
        try {
            eventRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }
}
