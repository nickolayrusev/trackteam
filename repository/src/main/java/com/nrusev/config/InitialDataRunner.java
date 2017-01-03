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

        if (user.getTeamPools().isEmpty()) {
            TeamPool pool = new TeamPool();
            pool.setName("over kings !");
            pool.setDescription("teams that scores a lot");
            pool.setUser(user);

            List<Team> chelsea = teamRepository.findByTitle("Chelsea");
            List<Team> arsenal = teamRepository.findByTitleIgnoreCase("arsenal");

            pool.getTeams().add(chelsea.get(0));
            pool.getTeams().add(arsenal.get(0));

            teamPoolRepository.save(pool);

            user.getTeamPools().add(pool);
            userRepository.save(user);
        }

    }
}
