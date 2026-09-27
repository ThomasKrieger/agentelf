package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.type.TypeRepo;
import org.agentelf.type.UpdateTypeRepoWithClassesFromRuntime;
import org.agentelf.type.UpdateTypeRepoWithSourceFromFolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitializeTypeRepo {

    @Value("${main-target-dir}")
    private String mainSrcDir;

    @Action(arguments = {}, returnVariable = "typeRepo")
    public TypeRepo initializeTypeRepo() {
        TypeRepo typeRepo = new TypeRepo();
        new UpdateTypeRepoWithSourceFromFolder().update(typeRepo.getReferenceTypeRepo(),mainSrcDir);
        new UpdateTypeRepoWithClassesFromRuntime().update(typeRepo.getReferenceTypeRepo());
        return typeRepo;
    }
}