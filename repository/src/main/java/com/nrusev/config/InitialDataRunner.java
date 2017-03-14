package com.nrusev.config;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.nrusev.repository.TeamPoolRepository;
import com.nrusev.repository.TeamRepository;
import com.nrusev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nikolay Rusev on 3.1.2017 г..
 */
@Component
public class InitialDataRunner implements ApplicationRunner {

    private final TeamPoolRepository teamPoolRepository;

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    @Autowired
    public InitialDataRunner(TeamPoolRepository teamPoolRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.teamPoolRepository = teamPoolRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("loading initial data ....");
        User user = userRepository.findByUserName("nrusev").orElse(new User("nickolay", "rusev", "nrusev"));

        if(user.getId()==null)
            user = this.userRepository.save(user);

        if (user.getTeamPools().isEmpty()) {
            TeamPool pool = new TeamPool();
            pool.setName("Favs kings !");
            pool.setDescription("teams that scores a lot");
            pool.setUser(user);

            Team leeds = teamRepository.findOne(1160L);
            Team levski = teamRepository.findOne(329L);
            Team benfica = teamRepository.findOne(393L);
            Team atletico = teamRepository.findOne(1227L);

            pool.getTeams().add(leeds);
            pool.getTeams().add(levski);
            pool.getTeams().add(benfica);
            pool.getTeams().add(atletico);

            teamPoolRepository.save(pool);

            user.getTeamPools().add(pool);
            userRepository.save(user);
        }

    }
}
