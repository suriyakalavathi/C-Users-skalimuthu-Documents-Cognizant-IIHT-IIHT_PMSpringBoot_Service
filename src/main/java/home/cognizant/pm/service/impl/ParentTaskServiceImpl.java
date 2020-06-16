package home.cognizant.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.cognizant.pm.service.api.ParestTaskService;
import home.cognizant.pm.service.entity.ParentTask;
import home.cognizant.pm.service.repository.ParentTaskRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@Transactional
public class ParentTaskServiceImpl implements ParestTaskService {

    @Autowired
    ParentTaskRepository parentTaskRepository;

    @Override
    public ParentTask add(ParentTask parentTask) {       
        return parentTaskRepository.save(parentTask);
    }

    @Override
    public ParentTask edit(ParentTask parentTask) {      
        return parentTaskRepository.save(parentTask);    }

    @Override
    public void delete(long parentTaskId) {       
        parentTaskRepository.deleteById(parentTaskId);
    }

    @Override
    public ParentTask get(long parentTaskId) {      
        return parentTaskRepository.findById(parentTaskId).orElse(null);
    }

    @Override
    public List<ParentTask> getAll() {
              return parentTaskRepository.findAll();
    }


}
