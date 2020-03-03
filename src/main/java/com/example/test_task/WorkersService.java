package com.example.test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WorkersService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PositionsRepository positionsRepository;

    @Transactional
    public void addWorker(Worker worker){
        workerRepository.save(worker);
    }

    @Transactional
    public void addPosition(Position position){
        positionsRepository.save(position);
    }

    @Transactional
    public void deleteWorkers(long[] idList){
        for (long id : idList) workerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Position> findPositions(){
        return positionsRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Worker> findAll(Pageable pageable) {
        return workerRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly=true)
    public List<Worker> findByPosition(Position position, Pageable pageable) {
        return workerRepository.findByPosition(position, pageable);
    }

    @Transactional(readOnly = true)
    public long countByPositions(Position position) {
        return workerRepository.countByPosition(position);
    }

    @Transactional(readOnly=true)
    public List<Worker> findByPattern(String pattern, Pageable pageable) {
        return workerRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return workerRepository.count();
    }

    @Transactional(readOnly=true)
    public Position findPositions(long id) {
        return positionsRepository.findById(id).get();
    }


}
