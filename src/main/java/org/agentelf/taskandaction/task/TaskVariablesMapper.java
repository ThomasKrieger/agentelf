package org.agentelf.taskandaction.task;

import org.agentelf.taskandaction.RunVariables;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskVariablesMapper {

    TaskVariablesMapper INSTANCE = Mappers.getMapper(TaskVariablesMapper.class);

    RunVariables toRunVariables(TaskVariables taskVariables);


}
