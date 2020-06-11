package home.cognizant.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.cognizant.pm.service.api.ParestTaskService;
import home.cognizant.pm.service.entity.ParentTaskObject;
import home.cognizant.pm.service.repository.ParentTaskRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@Transactional
public class ParentTaskServiceImpl implements ParestTaskService {

    @Autowired
    ParentTaskRepository parentTaskRepository;

    @Override
    public ParentTaskObject add(ParentTaskObject parentTask) {       
        return parentTaskRepository.save(parentTask);
    }

    @Override
    public ParentTaskObject edit(ParentTaskObject parentTask) {      
        return parentTaskRepository.save(parentTask);    }

    @Override
    public void delete(long parentTaskId) {       
        parentTaskRepository.deleteById(parentTaskId);
    }

    @Override
    public ParentTaskObject get(long parentTaskId) {      
        return parentTaskRepository.findById(parentTaskId).orElse(null);
    }

    @Override
    public List<ParentTaskObject> getAll() {
              return parentTaskRepository.findAll();
    }


}
