package org.example.baitap2.models;

public class CandidateSkills {
    private int id;
    private int candidateId;
    private int skillId;
    public CandidateSkills() {
    }

    public CandidateSkills(int id, int candidateId, int skillId) {
        this.id = id;
        this.candidateId = candidateId;
        this.skillId = skillId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
