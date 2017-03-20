package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.mvp.MvpView;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
public interface PoolView extends MvpView {
    void init(TeamPool pool, List<Team> allTeams);
    void addTeam(Team team);
    void removeTeam(Team team);

    class AddTeamEvent extends TeamEvent{

        public AddTeamEvent(Component source) {
            super(source);
        }

        public AddTeamEvent(Component source, Team team) {
            super(source, team);
        }
    }
    class DeleteTeamEvent extends TeamEvent{

        public DeleteTeamEvent(Component source) {
            super(source);
        }

        public DeleteTeamEvent(Component source, Team team) {
            super(source, team);
        }
    }

    class TeamEvent extends Component.Event {
        private Team team;

        public TeamEvent(Component source) {
            super(source);
        }


        public TeamEvent(Component source, Team team) {
            super(source);
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }

        @Override
        public String toString() {
            return "TeamEvent{" +
                    "team=" + team +
                    '}';
        }
    }

    class SaveTeamPoolEvent extends Component.Event{

        public SaveTeamPoolEvent(Component source) {
            super(source);
        }
    }

    class CancelTeamPoolEvent extends Component.Event{
        public CancelTeamPoolEvent(Component source) {
            super(source);
        }
    }
}
