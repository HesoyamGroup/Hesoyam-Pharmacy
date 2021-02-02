package com.hesoyam.pharmacy.feedback.service;

import com.hesoyam.pharmacy.feedback.dto.ReplyDTO;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidReplyRequest;
import com.hesoyam.pharmacy.feedback.model.Reply;

public interface IReplyService {
    Reply create(Reply reply);
    Reply reply(ReplyDTO replyDTO) throws InvalidReplyRequest;
}
